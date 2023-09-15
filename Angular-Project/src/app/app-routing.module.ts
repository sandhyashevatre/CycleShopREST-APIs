import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// Import HttpClientModule in your app.module.ts
import { HttpClientModule } from '@angular/common/http';
// Make HTTP request in your component
import { HttpClient } from '@angular/common/http';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }




