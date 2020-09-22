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
  displayedColumns = ['name', 'date', 'duration', 'take'];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.tests = this.course.tests;
    console.log(this.tests);
  }

  takeTest(id: number) {
    this.router.navigateByUrl('test/' + id);
  }


  testButtonAvaiable(test: Test) {
    const now = new Date();
    const newDateObj = new Date(new Date(test.date).getTime() + test.duration * 60000);
    return (now.getTime() > new Date(test.date).getTime()) && now.getTime() < newDateObj.getTime();
  }

}
