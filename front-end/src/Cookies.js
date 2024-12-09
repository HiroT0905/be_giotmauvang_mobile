// Hàm setCookies: Lưu thực thể user vào cookie
function setCookies(name, user, maxAge = 3600) {
    if (typeof user !== "object") {
      console.error("User must be an object.");
      return;
    }
    const userString = JSON.stringify(user);
    document.cookie = `${name}=${encodeURIComponent(userString)}; path=/; max-age=${maxAge}`;
  }
  
  // Hàm getCookies: Lấy giá trị cookie và parse thành object
  function getCookies(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) {
      const cookieValue = parts.pop().split(";").shift();
      try {
        return JSON.parse(decodeURIComponent(cookieValue));
      } catch (error) {
        console.error("Failed to parse cookie value:", error);
        return null;
      }
    }
    return null;
  }
  