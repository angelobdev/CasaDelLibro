import {Component} from '@angular/core';
import {faInstagram, faGithub, faLinkedin, faTwitter} from "@fortawesome/free-brands-svg-icons";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent {

  faInstagram = faInstagram;
  faGithub = faGithub;
  faLinkedin = faLinkedin;
  faTwitter = faTwitter;
}
