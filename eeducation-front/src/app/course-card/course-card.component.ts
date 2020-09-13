import {Component, Input, OnInit} from '@angular/core';
import {CourseBasic} from '../model/CourseBasic';

@Component({
  selector: 'app-course-card',
  templateUrl: './course-card.component.html',
  styleUrls: ['./course-card.component.scss']
})
export class CourseCardComponent implements OnInit {

  @Input() course: CourseBasic;

  constructor() {
  }

  ngOnInit(): void {
  }

}
