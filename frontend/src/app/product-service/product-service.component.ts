import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";

@Component({
  selector: 'app-product-service',
  templateUrl: './product-service.component.html',
  styleUrls: ['./product-service.component.scss']
})
export class ProductServiceComponent implements OnInit {

  constructor(private http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Page>("api/v1/product")
  }

  public delete(id: number){
    return this.http.delete("api/v1/product/{id}")
  }

  ngOnInit(): void {
  }

}
