import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Paper } from './models/paper';
import { User } from './models/user';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuathenticateLoginService {
	@Output() update: EventEmitter<Paper[]> = new EventEmitter();

	updatePaper(paper) {
		var papers = this.http.post<Paper[]>(this.url + 'save?username=' + sessionStorage.getItem('username'), paper);
		papers.subscribe(data => {
			this.update.emit(data);
		})

	}

	deletePaper(paper: Paper) {
		return this.http.delete<Paper[]>(this.url + "delete?username=" +
			sessionStorage.getItem("username")+"&paperId=" + paper.paperId);

	}

	createPaper() {
		return this.http.put<Paper[]>(this.url + "new?username=" + sessionStorage.getItem("username"), {

		});
	}

	getPapers(): Observable<Paper[]> {
		return this.http.get<Paper[]>(this.url + "papers?username=" + sessionStorage.getItem('username'));
	}



  	constructor(private http: HttpClient, private router: Router) { }

  	authenticate(username: string, password: string) {
		this.http.post<User>(this.url + 'login', {
			'username': username,
			'password': password
		}).subscribe(o => {
			sessionStorage.setItem("username", o.username);
		});
		if (sessionStorage.getItem("username") != "")
			return true;
		return false;
  	}

	logOut() {
		sessionStorage.removeItem('username');
		this.router.navigate(['']);
	}

	register(username, password) {
		let registered = false;
		this.http.post(this.url + 'register', {
			username: username,
			password: password
		}).subscribe(o => {
			if (o) registered = true;
		});
		return registered;
	}

  	url: string = 'http://localhost:8080/';
}
