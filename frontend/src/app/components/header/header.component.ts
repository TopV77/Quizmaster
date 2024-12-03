import { Component, ElementRef, ViewChild } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
declare var bootstrap: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(private router: Router) { }


  /* 
    Burger menu is not cloasing by default on navigation (BS default)
    subscribe to router and make sure to trigger collapse after
    navigation was finished
  */
  @ViewChild('navbarCollapse') navbarCollapse!: ElementRef;

  ngAfterViewInit(): void {
    // Subscribe to router events to close the menu on navigation
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.closeNavbar();
      }
    });
  }

  private closeNavbar(): void {
    const navbar = this.navbarCollapse.nativeElement;
    // Check if the navbar is open and close it if necessary
    if (navbar.classList.contains('show')) {
      const collapseElement = new bootstrap.Collapse(navbar);
      collapseElement.hide();
    }
  }

}
