import http from 'k6/http';
import { sleep } from 'k6';

export default function () {
  var server_list = ["127.0.0.1:8080", "127.0.0.1:8081", "127.0.0.1:8082"]
  var endpoint_list = ["/api/members"]
  const payload = JSON.stringify({
    name: 'kim',
    email: 'kimho314@test.com',
    password: '1234',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };
  server_list.forEach(function(server) {
    endpoint_list.forEach(function(endpoint) {
      http.post("http://" + server + endpoint, payload, params);
    });
  });
  sleep(0.5);
}