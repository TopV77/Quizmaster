import {Component} from '@angular/core';
import {TagDetail} from "../../interfaces/tagDetail";
import {TagService} from "../../services/tag/tag.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-tag-details',
  templateUrl: './tag-details.component.html',
  styleUrl: './tag-details.component.css'
})
export class TagDetailsComponent {
  tagDetail?: TagDetail;

  constructor(private tagService: TagService, private activatedRoute: ActivatedRoute, private router: Router) {
    const id = activatedRoute.snapshot.paramMap.get('id');
    this.tagService.getTagById(parseInt(id!)).subscribe(data => this.tagDetail = data);
  }
}
