import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../service/authentication.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SearchService} from '../service/search.service';
import {CourseBasic} from '../model/CourseBasic';
import {School} from '../model/School';
import {Router} from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  searchForm: FormGroup;
  coursesFound: Array<CourseBasic>;
  schoolsFound: Array<School>;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService,
              private snackBar: MatSnackBar, private searchService: SearchService, private router: Router) {
  }

  get name() {
    return this.searchForm.controls.name.value as string;
  }

  get searchType() {
    return this.searchForm.controls.searchType.value as string;
  }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      searchType: ['', [
        Validators.required
      ]]
    });
  }

  onSearchSubmit() {
    this.coursesFound = Array();
    this.schoolsFound = Array();
    const name = this.name;
    const type = this.searchType;
    if (type === 'COURSE') {
      this.searchService.searchCourses(name).subscribe(
        response => {
          this.coursesFound = response;
        }, error => {
          this.snackBar.open('Error searching courses');
        }
      );
    } else if (type === 'SCHOOL') {
      this.searchService.searchSchools(name).subscribe(
        response => {
          this.schoolsFound = response;
        }, error => {
          this.snackBar.open('Error searching schools');
        }
      );
    }
  }

  goCourse(id) {
    this.router.navigateByUrl('course/' + id);
  }

  goSchool(id) {
    this.router.navigateByUrl('school/' + id);
  }
}
