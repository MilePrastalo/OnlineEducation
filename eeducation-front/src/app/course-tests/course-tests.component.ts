import {Component, Input, OnInit} from '@angular/core';
import {Test} from '../model/Test';
import {Router} from '@angular/router';
import {Course} from '../model/Course';

@Component({
  selector: 'app-course-tests',
  templateUrl: './course-tests.component.html',
  styleUrls: ['./course-tests.component.scss']
})
export class CourseTestsComponent implements OnInit {

  tests: Array<Test>;
  @Input() course: Course;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.tests = this.course.tests;
  }

  takeTest(id: number) {
    this.router.navigateByUrl('test/' + id);
  }

  goCreateTest() {
    this.router.navigateByUrl('create-test/' + this.course.id);
  }

}
