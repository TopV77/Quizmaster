import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-add-card',
  templateUrl: './add-card.component.html',
  styleUrl: './add-card.component.css'
})
export class AddCardComponent {
  @Input() mode: "category" | "quiz" | "tag" = "tag";

  setLinkUrl(mode: string): string {
    switch (mode) {
      case "category":
        return '/category/create';
      case "quiz":
        return '/quiz/create';
      default:
        return '/tag/create';
    }
  }

}
