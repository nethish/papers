import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { PaperContainerComponent } from './paper-container/paper-container.component';
import { PaperComponent } from './paper/paper.component';
import { FormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule, MatButtonModule, MatGridListModule, MatInputModule } from '@angular/material';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { AuthGaurdService } from './auth-gaurd.service';

const routes: Routes = [
	{ path: '', component: LoginComponent },
	{ path: 'papers', component: PaperContainerComponent, canActivate: [AuthGaurdService] }
];

@NgModule({
	declarations: [
		AppComponent,
		PaperContainerComponent,
		PaperComponent,
		LoginComponent,
	],
	imports: [
		BrowserModule,
		RouterModule,
		RouterModule.forRoot(routes),
		FormsModule,
		BrowserAnimationsModule,
		MatCardModule,
		MatButtonModule,
		MatGridListModule,
		MatInputModule,
		HttpClientModule
	],
	providers: [],
	bootstrap: [AppComponent],
	schemas: [ CUSTOM_ELEMENTS_SCHEMA ] 
})
export class AppModule { }
