package gov.iti.jets.testing.day2.shopping.presentation;

import jakarta.servlet.http.HttpServletRequest;

public enum RequestAttributes {
    CREATED_ORDER,
    CREATED_USER;

    public void set(HttpServletRequest req, Object value) {
        req.setAttribute(this.name(), value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(HttpServletRequest req) {
        return (T) req.getAttribute(this.name());
    }
}
