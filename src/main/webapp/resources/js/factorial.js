/**
 * 
 */
const inputDataEl = document.querySelector('#inputData');
const resEl = document.querySelector('#res');
const inputEl = document.querySelector('input');

inputEl.addEventListener('change', (e) => {
  console.log(e.target.value);
  inputValue = e.target.value;
  fetch(`../../05/factorial?op=${inputValue}`)
    .then(res => res.json())
    .then(res => {
    inputEl.value = inputValue;
      inputDataEl.innerHTML = inputValue+"! ";
      resEl.innerHTML = res;
    })
    .catch(err => console.log(err));
});