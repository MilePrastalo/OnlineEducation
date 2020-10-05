import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import {TestService} from '../service/test.service';
import {TestResultList} from '../model/TestResultList';

@Component({
  selector: 'app-test-results',
  templateUrl: './test-results.component.html',
  styleUrls: ['./test-results.component.scss']
})
export class TestResultsComponent implements OnInit {

  testId: number;
  testResults: Array<TestResultList>;
  displayedColumns = ['studentName', 'testResult'];

  constructor(private testService: TestService, private router: Router, private route: ActivatedRoute,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.testId = Number(this.route.snapshot.paramMap.get('id'));
    this.testService.viewTestResults(this.testId).subscribe(
      response => {
        this.testResults = response;
      },
      error => {
        this.snackBar.open('Error getting test results');
      }
    );
  }

  viewTest(id: number) {
    this.router.navigateByUrl('userTestResults/' + id);
  }
}
