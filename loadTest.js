import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '30s', target: 150 },
        { duration: '30s', target: 180 },
        { duration: '20s', target: 0 },
    ],
};

export default function () {
    const res = http.get('http://host.docker.internal:8080/event');
    check(res, {
        'status was 200': (r) => r.status === 200,
        'event count was greater than 0': (r) => r.body.length > 0
    });
    sleep(1);
}

export function setup() {
    const url = 'http://host.docker.internal:8080/event';
    const payload = JSON.stringify({
        date: '2023-01-26',
        start: '18:00',
        end: '20:00'
    });
    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);
    check(res, { 'status was 201': (r) => r.status === 201 });
}