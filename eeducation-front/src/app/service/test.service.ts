import {Injectable} from '@angular/core';
import {PathService} from './path.service';
import {HttpClient} from '@angular/common/http';
import {CreateTest} from '../model/CreateTest';
import {Observable} from 'rxjs';
import {Test} from '../model/Test';
import {TestResult} from '../model/TestResult';
import {TestResultList} from '../model/TestResultList';
import {TestResultView} from '../model/TestResultView';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private path = '';

  constructor(private pathService: PathService, private http: HttpClient) {
    this.path = pathService.path + '/api/test';
  }

  createTest(createTest: CreateTest): Observable<Test> {
    return this.http.post<Test>(this.path, createTest);
  }

  getTest(testId: number): Observable<Test> {
    return this.http.get<Test>(this.path + '/' + testId);
  }

  studentSubmitsTest(testResult: TestResult): Observable<TestResult> {
    return this.http.post<TestResult>(this.path + '/student-test', testResult);
  }

  viewTestResults(testId: number): Observable<Array<TestResultList>> {
    return this.http.get<Array<TestResultList>>(this.path + '/testResults/' + testId);
  }

  viewStudentTestAnswers(testResultId: number): Observable<TestResultView> {
    return this.http.get<TestResultView>(this.path + '/testResult/' + testResultId);
  }
}
