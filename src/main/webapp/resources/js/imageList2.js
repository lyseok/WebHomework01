const selectEl = document.querySelector('[name="image"]');
console.log(selectEl);

fetch('../../image/csrImageListServlet2.do')
  .then(res => res.json())
  .then(res => {
    console.log(res)
    let options = res.map(fn => /* html */`<option>${fn}</option>`).join("\n");
    selectEl.innerHTML = options;
  });

selectEl.addEventListener('change', (e) => {
  console.log(e.target.value);
  resultArea.innerHTML = /* html */`
    <img src="../../image/streaming.do?${selectEl.name}=${e.target.value}" alt="" />
  `;
});