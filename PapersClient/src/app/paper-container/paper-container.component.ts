import { Component, OnInit, Input } from '@angular/core';
import { Paper } from '../models/paper';
import { AuathenticateLoginService } from '../auathenticate-login.service';

@Component({
	selector: 'app-paper-container',
	templateUrl: './paper-container.component.html',
	styleUrls: ['./paper-container.component.css']
})

export class PaperContainerComponent implements OnInit {

	constructor(private auth: AuathenticateLoginService) {
		
	}

	ngOnInit() {
		this.auth.getPapers().subscribe(data => this.papers = data);
		this.auth.update.subscribe(papers => this.papers = papers);
	}

	selectPaper(paper: Paper) {
		this.selectedPaper = paper;
	}

	createPaper () {
		this.auth.createPaper().subscribe(papers => {
			console.log("Got papers ", papers);
			this.papers = papers;
			if (this.papers.length)
				this.selectedPaper = this.papers[this.papers.length-1];
			else this.createPaper();
		});
		// this.selectPaper(this.papers[this.papers.length-1]);
	}

	logOut() {
		this.auth.logOut();
	}

	deletePaper () {
		console.log(this.selectedPaper);
		this.auth.deletePaper(this.selectedPaper).subscribe(data => {
			console.log("IDHUDHANDA", data)
			this.papers = data
		});
		if (this.papers.length)
			this.selectedPaper = this.papers[this.papers.length-1]
		else this.createPaper();
	}

	selectedPaper: Paper;
	papers: Paper[] = [];
}
