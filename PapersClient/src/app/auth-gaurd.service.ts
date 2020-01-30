import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router'

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService implements CanActivate {

  constructor(private router: Router) { }

  canActivate() {
	  if(sessionStorage.getItem("username") != "" && sessionStorage.getItem("username") != null) {
		  return true;
	  }
	  this.router.navigate(['']);
  }
}
