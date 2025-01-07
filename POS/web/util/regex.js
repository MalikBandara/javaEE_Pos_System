


export let NAME = /^[A-Za-z]+(?: [A-Za-z]+)*$/;
export let ADDRESS =  /^(?![A-Za-z]+$)(?!\d+$)[A-Za-z0-9\s/\\.,-]+$/;
export let SALARY = /^\$?\d{1,3}(?:,\d{3})*(?:\.\d{2})?$/;
export let EMAIL = /^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$/;
export let TEL = /^[0]([1-9]{2})([0-9]){7}$/;
export let PRICE = /^([0-9]){1,}[.]([0-9]){1,}$/;
export let QTY = /^[0-9]{1,5}$/;

