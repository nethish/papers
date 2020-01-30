import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { Paper } from '../models/paper';
import { AuathenticateLoginService } from '../auathenticate-login.service';

@Component({
  	selector: 'app-paper',
  	templateUrl: './paper.component.html',
	styleUrls: ['./paper.component.css']
})
export class PaperComponent implements OnInit {

  	constructor(private service: AuathenticateLoginService) { }

  	ngOnInit() {
	}

	saveContent() {
		this.service.updatePaper(this.paper);
	}

	resetContent() {
		this.paper.content = '';
		console.log('Reset editor content')
	}

	@Input() paper: Paper;
}
