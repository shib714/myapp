import { Injectable, computed, signal } from '@angular/core';
import { IProduct } from '../products/product.service';

@Injectable({
    providedIn: 'root'
})
export class CartService {

    private nextLineId = 1;

    // Manage state with signals
    cartItems = signal<CartItem[]>([]);

    // Total up the extended price for each item
    //subTotal = computed(() => this.cartItems().reduce((total, item) => total + (item.product.quantity * Number(item.porduct.price), 0));
    subTotal = computed(() =>
        this.cartItems().reduce((total, item) => total + (item.quantity * Number(item.product.price)), 0));

    // Delivery is free if spending more than 100,000 credits
    deliveryFee = computed(() => this.subTotal() < 100 ? 10 : 0);

    cartCount = computed(() => this.cartItems().reduce((acc, item) => acc + item.quantity, 0));
    savings = computed(() => this.cartCount() > 3 ? this.subTotal() * 0.05 : this.subTotal());
    discountedTotal = computed(() =>
        this.cartCount() > 3 ? this.subTotal() * 0.95 : this.subTotal()
    );
    // Tax could be based on shipping address zip code
    //tax = computed(() => Math.round(this.subTotal() * 13.00) / 100);
    tax = computed(() => +(this.subTotal() * 0.13).toFixed(2));

    // Total price
    totalPrice = computed(() => this.subTotal() + this.deliveryFee() + this.tax());

    private getProductIdentity(product: IProduct): string {
        return [product.id, product.title, product.price, product.category, product.description, product.image]
            .map(value => String(value))
            .join('|');
    }

    private createCartItem(product: IProduct): CartItem {
        return {
            lineId: `line-${this.nextLineId++}`,
            product,
            quantity: 1
        };
    }

    // Add the product to the cart 
    // If the item is already in the cart, increase the quantity
    addToCart(product: IProduct): void {
        const productIdentity = this.getProductIdentity(product);
        const index = this.cartItems().findIndex(item => this.getProductIdentity(item.product) === productIdentity);

        if (index === -1) {
            this.cartItems.update(items => [...items, this.createCartItem(product)]);
        } else {
            this.cartItems.update(items =>
                items.map((item, i) =>
                    i === index ? { ...item, quantity: item.quantity + 1 } : item
                )
            );
        }
    }
    // Remove the item from the cart
    removeFromCart(cartItem: CartItem): void {
        this.cartItems.update(items => items.filter(item => item.lineId !== cartItem.lineId));
    }

    updateInCart(cartItem: CartItem, quantity: number) {
        if (quantity <= 0) {
            this.removeFromCart(cartItem);
            return;
        }

        this.cartItems.update(items =>
            items.map(item => item.lineId === cartItem.lineId ?
                { ...item, quantity } : item));
    }
    clearCart() {
        this.cartItems.set([]);
    }


}

export interface Cart {
    cartItems: CartItem[]
}

export interface CartItem {
    lineId: string;
    product: IProduct;
    quantity: number;
}

// You could move this to a shared file
// and reuse it for every entity in the application
type ActionType = 'add' | 'update' | 'delete';

export interface Action<T> {
    item: T;
    action: ActionType;
}