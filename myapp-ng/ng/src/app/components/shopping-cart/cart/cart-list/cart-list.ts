import { CommonModule } from '@angular/common';
import { Component, inject, ChangeDetectionStrategy } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CartService } from '../cart.service';
import { CartItemComponent } from '../cart-item/cart-item.component';
import { CartSummary } from '../cart-summary/cart-summary';

@Component({
  selector: 'cart-list',
  standalone: true,
  template: `
    <div class="cart-page-container">
      <div class="cart-items-list">
        <h2>Your Shopping Cart</h2>
        @if (cartCount() === 0) {
          <p>Your shopping cart is empty.</p>
          <div class="empty-cart-actions">
            <button mat-stroked-button color="primary" routerLink="/products">
              Start Shopping
            </button>
          </div>
        } @else {
          <h2>Your shopping cart contains: {{ cartCount() }} items.</h2>
          @for (item of cartItems(); track item.lineId) {
            <cart-item [item]="item"></cart-item>
          }
        }
      </div>
      @if (cartCount()> 0) {
        <div class="cart-summary-container">
          <cart-summary></cart-summary>
        </div>
      }
    </div>
  `,
  styleUrl: './cart-list.scss',
  changeDetection: ChangeDetectionStrategy.Eager,
  imports: [CommonModule, CartItemComponent, CartSummary, MatButtonModule, RouterLink],
})
export class CartList {
  cartService = inject(CartService);
  cartItems = this.cartService.cartItems;
  cartCount = this.cartService.cartCount;
}
