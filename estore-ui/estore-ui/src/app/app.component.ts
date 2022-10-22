import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Rochester Parkour';

  constructor(private titleservice:Title){
    this.titleservice.setTitle(this.title)
  }
}
