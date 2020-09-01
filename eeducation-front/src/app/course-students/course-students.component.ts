import {Component, Input, OnInit} from '@angular/core';
import {Course} from '../model/Course';

@Component({
  selector: 'app-course-students',
  templateUrl: './course-students.component.html',
  styleUrls: ['./course-students.component.scss']
})
export class CourseStudentsComponent implements OnInit {

  @Input() course: Course;
  displayedColumns: string[] = ['name', 'email'];

  constructor() {
  }

  ngOnInit(): void {
  }

}
