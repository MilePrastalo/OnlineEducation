import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { IndexComponent } from './index/index.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AuthenticationService} from './service/authentication.service';
import {PathService} from './service/path.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {TokenInterceptorService} from './service/token-interceptor.service';
import {MaterialModule} from './material/material.module';
import { HomeComponent } from './home/home.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { TeacherHomeComponent } from './teacher-home/teacher-home.component';
import { CourseDetailsComponent } from './course-details/course-details.component';
import { CourseAbsencesComponent } from './course-absences/course-absences.component';
import { CourseStudentsComponent } from './course-students/course-students.component';
import { CourseLessonsComponent } from './course-lessons/course-lessons.component';
import { CourseTestsComponent } from './course-tests/course-tests.component';
import { SchoolDetailsComponent } from './school-details/school-details.component';
import { SchoolInvitationsComponent } from './school-invitations/school-invitations.component';
import { CreateTestComponent } from './create-test/create-test.component';
import { TestComponent } from './test/test.component';
import { CreateAbsenceComponent } from './create-absence/create-absence.component';
import { CreateLessonComponent } from './create-lesson/create-lesson.component';
import { LessonComponent } from './lesson/lesson.component';
import { CourseGradesComponent } from './course-grades/course-grades.component';
import { AddGradeComponent } from './add-grade/add-grade.component';
import { StudentHomeComponent } from './student-home/student-home.component';
import { StudentGradesComponent } from './student-grades/student-grades.component';
import { ImageDialogComponent } from './image-dialog/image-dialog.component';
import { CommentComponent } from './comment/comment.component';
import { ReplyDialogComponent } from './reply-dialog/reply-dialog.component';
import { EditLessonComponent } from './edit-lesson/edit-lesson.component';
import { QuestionComponent } from './question/question.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {CdkTableModule} from '@angular/cdk/table';
import { SchoolHomeComponent } from './school-home/school-home.component';
import { QuestionDialogComponent } from './question-dialog/question-dialog.component';
import { AnswerDialogComponent } from './answer-dialog/answer-dialog.component';
import { SearchComponent } from './search/search.component';
import { CourseCardComponent } from './course-card/course-card.component';
import { SchoolCardComponent } from './school-card/school-card.component';
import { TestGradingComponent } from './test-grading/test-grading.component';
import {OwlDateTimeModule, OwlNativeDateTimeModule} from 'ng-pick-datetime';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    HomeComponent,
    CreateCourseComponent,
    EditCourseComponent,
    TeacherHomeComponent,
    CourseDetailsComponent,
    CourseAbsencesComponent,
    CourseStudentsComponent,
    CourseLessonsComponent,
    CourseTestsComponent,
    SchoolDetailsComponent,
    SchoolInvitationsComponent,
    CreateTestComponent,
    TestComponent,
    CreateAbsenceComponent,
    CreateLessonComponent,
    LessonComponent,
    CourseGradesComponent,
    AddGradeComponent,
    StudentHomeComponent,
    StudentGradesComponent,
    ImageDialogComponent,
    CommentComponent,
    ReplyDialogComponent,
    EditLessonComponent,
    QuestionComponent,
    NavbarComponent,
    UserProfileComponent,
    SchoolHomeComponent,
    QuestionDialogComponent,
    AnswerDialogComponent,
    SearchComponent,
    CourseCardComponent,
    SchoolCardComponent,
    TestGradingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CdkTableModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,

  ],
  providers: [
    AuthenticationService,
    PathService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
