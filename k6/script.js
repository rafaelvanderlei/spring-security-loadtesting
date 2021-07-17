import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  vus: 150,
  duration: '10s',
};

export default function () {
	
	const url = (__ENV.PRIVATE === 'true') ? `http://username:password@host.docker.internal:8080/private`: 'http://host.docker.internal:8080/public';
	const res = http.get(url);
	
	check(res, {
	    'status is 200': (r) => r.status === 200
	});
	
	//think time
	sleep(1);
}