import {Component, Input} from '@angular/core';
import {CategoryShort} from "../../../interfaces/categoryShort";

@Component({
  selector: 'app-category-card',
  templateUrl: './category-card.component.html',
  styleUrl: './category-card.component.css'
})
export class CategoryCardComponent {
  @Input() category?: CategoryShort;
}
