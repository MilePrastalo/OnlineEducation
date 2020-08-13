import {Component, OnInit} from '@angular/core';
import {Course} from '../model/Course';
import {CourseService} from '../service/course.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  course: Course;
  courseId: number;

  constructor(private courseService: CourseService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.courseService.getCourse(this.courseId).subscribe(
      response => {
        this.course = response;
      },
      error => {
        alert(error);
      }
    );
  }

  edit() {
    this.router.navigateByUrl('edit-course/' + this.courseId);
  }

  archive() {
    this.courseService.archiveCourse(this.courseId).subscribe(
      response => {
        this.snackBar.open('Course has been archived');
      },
      error => {
        this.snackBar.open('Error trying to archive course');

      }
    );
  }

}
