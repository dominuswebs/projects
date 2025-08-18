let draggedNode = null;
let offsetX = 0;
let offsetY = 0;
const palette = document.getElementById('palette');
const canvas = document.getElementById('canvas');

document.addEventListener('dragstart', e => {
  if (e.target.classList.contains('draggable')) {
    draggedNode = e.target;
    sourceZone = e.target.closest('#palette') ? 'palette' : 'canvas';

    const rect = e.target.getBoundingClientRect();
    offsetX = e.clientX - rect.left;
    offsetY = e.clientY - rect.top;
  }
});

canvas.addEventListener('dragover', e => e.preventDefault());

canvas.addEventListener('drop', e => {
  e.preventDefault();

  const canvasRect = canvas.getBoundingClientRect();
  const left = e.clientX - canvasRect.left - offsetX;
  const top = e.clientY - canvasRect.top - offsetY;

  let elementToDrop;

  if (sourceZone === 'palette') {
    elementToDrop = draggedNode.cloneNode(true);
    elementToDrop.draggable = true;
    elementToDrop.addEventListener('dragstart', dragStartHandler);

    if (elementToDrop.classList.contains('group')) {
      makeDroppable(elementToDrop);
    }
  } else {
    elementToDrop = draggedNode;
  }

  elementToDrop.style.position = 'absolute';
  elementToDrop.style.left = `${left}px`;
  elementToDrop.style.top = `${top}px`;
  elementToDrop.classList.add('dropped');

  // Always append to canvas so it leaves any group
  canvas.appendChild(elementToDrop);

  draggedNode = null;
  sourceZone = null;
});

function dragStartHandler(e) {
  draggedNode = e.target;
  sourceZone = 'canvas';
  const rect = e.target.getBoundingClientRect();
  offsetX = e.clientX - rect.left;
  offsetY = e.clientY - rect.top;
}

// Make an element act as a droppable zone
function makeDroppable(target) {
  target.addEventListener('dragover', e => e.preventDefault());
  target.addEventListener('drop', e => {
    e.preventDefault();

    const rect = target.getBoundingClientRect();
    const left = e.clientX - rect.left - offsetX;
    const top = e.clientY - rect.top - offsetY;

    // If dragging from palette, clone
    let elementToDrop;
    if (sourceZone === 'palette') {
      elementToDrop = draggedNode.cloneNode(true);
      elementToDrop.draggable = true;
      elementToDrop.addEventListener('dragstart', dragStartHandler);
    } else {
      elementToDrop = draggedNode;
    }

    elementToDrop.style.position = 'relative';
    elementToDrop.style.left = `${left}px`;
    elementToDrop.style.top = `${top}px`;

    if (!target.contains(elementToDrop)) {
      target.appendChild(elementToDrop);
    }

    draggedNode = null;
    sourceZone = null;
  });
}