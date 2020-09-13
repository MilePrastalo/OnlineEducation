import {Component, Input, OnInit} from '@angular/core';
import {School} from '../model/School';

@Component({
  selector: 'app-school-card',
  templateUrl: './school-card.component.html',
  styleUrls: ['./school-card.component.scss']
})
export class SchoolCardComponent implements OnInit {

  @Input() school: School;

  constructor() {
  }

  ngOnInit(): void {
  }

}
