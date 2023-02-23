import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ReviewEditionPageRoutingModule } from './review-edition-routing.module';

import { ReviewEditionPage } from './review-edition.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReviewEditionPageRoutingModule
  ],
  declarations: [ReviewEditionPage]
})
export class ReviewEditionPageModule {}
