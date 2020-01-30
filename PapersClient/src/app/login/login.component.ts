import { Component, OnInit } from '@angular/core';
import { AuathenticateLoginService } from '../auathenticate-login.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	constructor(private loginSerice: AuathenticateLoginService, 
		private router: Router, private http: HttpClient) {
	}

  	ngOnInit() {
	}

	checkLogin() {
		if(this.loginSerice.authenticate(this.username, this.password)) {
			this.router.navigate(['papers']);
		} else {
			this.router.navigate(['']);
		}
	}

	register() {
		if (this.usernameRegister == '' || this.passwordRegister == '') {
			this.failRegister = "Provide valid username and password";
		}
		else {
			if (this.loginSerice.register(this.usernameRegister, this.passwordRegister)) {
				this.router.navigate(['papers']);
			} else {
				this.router.navigate(['']);
			}
		}
	}

	failRegister: string = '';
	usernameRegister: string = '';
	passwordRegister: string = '';

	username: string;
	password: string;
}
