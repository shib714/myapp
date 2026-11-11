import { Component, ChangeDetectionStrategy } from '@angular/core';
import { ProfileForm } from './sign-up/profile-form';

@Component({
  selector: 'composable-form',
  imports: [ProfileForm],
  changeDetection: ChangeDetectionStrategy.Eager,
  template: `<profile-form />`,
})
export class ComposableForm {}
