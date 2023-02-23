import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.page.html',
  styleUrls: ['./review-list.page.scss'],
})
export class ReviewListPage implements OnInit {

    reviews: any = ['Primera', 'Segunda', 'Tercera'];

  constructor() { }

  ngOnInit() {
  }

}
