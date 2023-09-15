import { Component } from '@angular/core';
import { Cycle } from '../cycle';
import { CycleService } from '../cycle.service';

@Component({
  selector: 'app-cycle-shop',
  templateUrl: './cycle-shop.component.html',
  styleUrls: ['./cycle-shop.component.css']
})
export class CycleShopComponent {
  allCycles : Cycle[] = [{
    id : 1,
    brand : 'Hero',
    stock : 100,
    numBorrowed : 0
  },
  {
    id : 2,
    brand : 'Herculas',
    stock : 200,
    numBorrowed :0
  }]
  borrowCycle(id: number,count:string){
    let cycle = this.allCycles.find(cyc => cyc.id == id);
    let numVal = 0;
    if(count!="")
      numVal = parseInt(count);
    if(cycle){
      if(cycle.stock-numVal>0){
        //pagal
        cycle.numBorrowed+=numVal;
        //em chesthundhi

      }
      else{//pagal
        console.error(`out of stock`);
      }
    }
  }
  restockCycle(id: number,count:string){
    let cycle = this.allCycles.find(cyc => cyc.id == id);
    let numVal = 0;
    if(count!="")
      numVal = parseInt(count);
    if(cycle){
      cycle.numBorrowed-=numVal;
    }
  }
}
