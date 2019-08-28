import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GameListComponent } from './game-list/game-list.component';
import { GamePlayComponent } from './game-play/game-play.component';

const routes: Routes = [
  { path: 'games', component: GameListComponent },
  { path: 'game-play', component: GamePlayComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }