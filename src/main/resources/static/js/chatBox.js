function scrollToBottom() {
  const chatContainer = document.querySelector('.chat-container');
  chatContainer.scrollTop = chatContainer.scrollHeight;

  // Immediate scroll to the bottom
  chatContainer.scrollIntoView({
    behavior: 'auto',
    block: 'end',
  });
}
