<section>
    <h2>Leave a Public Note/Question</h2>
    <form method="POST">
        <!-- CSRF -->
        <label>
            Name
            <input type="text" name="name" />
        </label>

        <label>
            Email
            <input type="email" name="email" />
        </label>
        
        <label>
            Message
            <textarea name="message" rows="4"></textarea>
        </label>
        <button>Send Message</button>
    </form>
</section>