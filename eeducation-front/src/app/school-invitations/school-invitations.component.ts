import { Component, OnInit } from '@angular/core';
import {School} from '../model/School';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SchoolService} from '../service/school.service';

@Component({
  selector: 'app-school-invitations',
  templateUrl: './school-invitations.component.html',
  styleUrls: ['./school-invitations.component.scss']
})
export class SchoolInvitationsComponent implements OnInit {

  schools: Array<School>;

  constructor(private snackBar: MatSnackBar, private schoolService: SchoolService) {
  }

  ngOnInit(): void {
    this.getInvitations();
  }

  getInvitations() {
    this.schoolService.getSchoolInvitations().subscribe(
      response => {
        this.schools = response;
      },
      error => {
        this.snackBar.open('Error getting school invitations');
      }
    );
  }

  acceptInvitation(schoolId: number) {
    this.schoolService.acceptSchoolInvitation(schoolId).subscribe(
      response => {
        this.getInvitations();
      },
      error => {
        this.snackBar.open('Error accepting invitation');
      }
    );
  }

  rejectInvitation(schoolId: number) {
    this.schoolService.rejectSchoolInvitation(schoolId).subscribe(
      response => {
        this.getInvitations();
      },
      error => {
        this.snackBar.open('Error rejecting invitation');
      }
    );
  }

}
