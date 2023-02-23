import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Review } from '../model/Review';
import { ReviewListService } from '../servicios/review-list.service';

@Component({
  selector: 'app-review-edition',
  templateUrl: './review-edition.page.html',
  styleUrls: ['./review-edition.page.scss'],
})
export class ReviewEditionPage implements OnInit {

    review?: Review;
  constructor(private route: ActivatedRoute,
    private reviewService: ReviewListService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
        this.review = params['review'];
    });
  }

  save() {
    if (!!this.review) {
        this.reviewService.save(this.review);
    }
  }

}
