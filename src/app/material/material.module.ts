import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatSelectModule} from '@angular/material/select';
import {MatSliderModule} from '@angular/material/slider';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
const MaterialModules = [
  MatInputModule,
  MatFormFieldModule,
  MatTableModule,
  MatGridListModule,
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatCardModule,
  MatMenuModule,
  MatButtonToggleModule,
  MatSelectModule,
  MatSliderModule,
  MatDialogModule
];

@NgModule({
  declarations: [],
  imports: [MaterialModules],
  exports : [MaterialModules]
})
export class MaterialModule { }
