package com.example.w9_assignment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticUiController {

    @GetMapping(value = {"/", "/index.html"}, produces = "text/html")
    public String index() {
        return """
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Microservice UI</title></head>
<body>
  <h1>Microservice Assignment UI</h1>

  <section>
    <h2>Configuration Data (/config)</h2>
    <pre id="config">Loading...</pre>
  </section>

  <section>
    <h2>Fibonacci Sequence (/fib?length=10)</h2>
    <pre id="fib">Loading...</pre>
  </section>

  <script>
    (async function () {
      try {
        const cfg = await fetch('/config').then(r => r.json());
        document.getElementById('config').textContent = JSON.stringify(cfg, null, 2);

        const fib = await fetch('/fib?length=10').then(r => r.json());
        document.getElementById('fib').textContent = JSON.stringify(fib, null, 2);
      } catch (e) {
        document.getElementById('config').textContent = 'Error loading /config';
        document.getElementById('fib').textContent   = 'Error loading /fib';
        console.error(e);
      }
    })();
  </script>
</body>
</html>
""";
    }
}
