import {Component, Input, Host } from '@angular/core';
import { CheckboxGroupComponent } from '../checkbox-group/checkbox-group.component';

@Component({
    selector: 'checkbox',
    templateUrl: `./checkbox.component.html`
})
export class CheckboxComponent {
    @Input() value: any;

    constructor(@Host() private checkboxGroup: CheckboxGroupComponent) {
    }

    toggleCheck() {
        this.checkboxGroup.addOrRemove(this.value);
    }

    isChecked() {
        return this.checkboxGroup.contains(this.value);
    }
}
