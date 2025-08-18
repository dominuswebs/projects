const dropArea = document.getElementById('drop-area');
let draggedType = null;

document.querySelectorAll('.draggable').forEach(item => {
  item.addEventListener('dragstart', (e) => {
    draggedType = e.target.dataset.type;
  });
});

document.addEventListener('dragover', (e) => {
  e.preventDefault(); // allow drop
});

document.addEventListener('drop', (e) => {
  e.preventDefault();

  const dropTarget = e.target.closest('#drop-area, .group-container');
  if (!dropTarget) return;

  if (draggedType === 'square') {
    const el = document.createElement('div');
    el.className = 'square';
    el.draggable = false; // we use custom dragging instead

    if (dropTarget.id === 'drop-area') {
      // Absolute positioned square
      el.style.position = 'absolute';
      const rect = dropArea.getBoundingClientRect();
      el.style.left = `${e.clientX - rect.left - 30}px`; // center adjustment
      el.style.top = `${e.clientY - rect.top - 30}px`;

      enableDrag(el, dropArea);
      dropArea.appendChild(el);
    } else {
      // Square dropped into group
      dropTarget.appendChild(el);
    }

  } else if (draggedType === 'group') {
    const groupEl = document.createElement('div');
    groupEl.className = 'group-container';
    groupEl.textContent = 'Group: ';
    dropTarget.appendChild(groupEl);
  }
});


function enableDrag(el, container) {
  let offsetX, offsetY, isDragging = false;

  el.addEventListener('mousedown', (e) => {
    isDragging = true;
    offsetX = e.clientX - el.offsetLeft;
    offsetY = e.clientY - el.offsetTop;
    el.style.cursor = 'grabbing';
  });

  document.addEventListener('mousemove', (e) => {
    if (!isDragging) return;
    const rect = container.getBoundingClientRect();
    el.style.left = `${e.clientX - rect.left - offsetX}px`;
    el.style.top = `${e.clientY - rect.top - offsetY}px`;
  });

  document.addEventListener('mouseup', () => {
    isDragging = false;
    el.style.cursor = 'grab';
  });
}