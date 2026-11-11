import { Component, computed, inject, ChangeDetectionStrategy } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatGridListModule } from '@angular/material/grid-list';
import { CartService } from '../../cart/cart.service';

import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';

@Component({
  selector: 'vehicle-detail-dialog',
  templateUrl: './vehicle-detail-dialog.html',
  styleUrls: ['./vehicle-detail-dialog.scss'],
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [MatDialogModule, MatButtonModule, MatGridListModule, CommonModule, MatCardModule],
})
export class VehicleDetailDialog {
  errorMessage = '';
  cartService = inject(CartService);
  vehicleService = inject(VehicleService);

  vehicle = this.vehicleService.selectedVehicle;

  pageTitle = computed(() =>
    this.vehicle() ? `${this.vehicle()?.name}` : 'No vehicle selected',
  );

  vehicleFilms = this.vehicleService.vehicleFilms;

  addToCart(vehicle: Vehicle | undefined): void {
    if (vehicle) {
      this.cartService.addToCart(vehicle);
    }
  }
}
