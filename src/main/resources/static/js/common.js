document.addEventListener("DOMContentLoaded", function() {
    // アカウントセンターのサブメニューの表示/非表示を切り替える
    const toggleAccountCenter = document.getElementById("toggleAccountCenter");
    const accountSubMenu = document.getElementById("accountSubMenu");

    toggleAccountCenter.addEventListener("click", function() {
        if (accountSubMenu.style.display === "none" || accountSubMenu.style.display === "") {
            accountSubMenu.style.display = "block"; // 表示する
        } else {
            accountSubMenu.style.display = "none";  // 非表示にする
        }
    });

    // ログインメニューのサブメニューの表示/非表示を切り替える
    const toggleLoginMenu = document.getElementById("toggleLoginMenu");
    const loginSubMenu = document.getElementById("loginSubMenu");

    toggleLoginMenu.addEventListener("click", function() {
        if (loginSubMenu.style.display === "none" || loginSubMenu.style.display === "") {
            loginSubMenu.style.display = "block"; // 表示する
        } else {
            loginSubMenu.style.display = "none";  // 非表示にする
        }
    });
});
