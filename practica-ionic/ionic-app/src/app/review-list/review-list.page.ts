import { Component, OnInit } from '@angular/core';
import { NavigationExtras } from '@angular/router';
import { NavController } from '@ionic/angular';
import { Review } from '../model/Review';
import { ReviewListService } from '../servicios/review-list.service';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.page.html',
  styleUrls: ['./review-list.page.scss'],
})
export class ReviewListPage implements OnInit {

    reviews: Review[] = [];

  constructor(private reviewService : ReviewListService,
            private navController : NavController
    ) { }

  ngOnInit() {
    this.reviewService.findAll().subscribe(resp => {
        this.reviews = resp;
    });
  }

  save(review: Review) {
    const NavExtra: NavigationExtras = {
        queryParams: {
            review
        }
    }
    this.navController.navigateForward('review-edition', NavExtra);
  }

}
