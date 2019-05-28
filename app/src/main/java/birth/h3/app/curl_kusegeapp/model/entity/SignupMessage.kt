package birth.h3.app.curl_kusegeapp.model.entity

import birth.h3.app.curl_kusegeapp.model.enums.MessageOwner

data class SignupMessage(
        val id: Int,
        val owner: MessageOwner,
        val message: String,
        val wait: Boolean
) {
    companion object {
        fun signUpMessages() = listOf(
                SignupMessage(1, MessageOwner.CURL, "Curlをインストールいただきありがとうございます！", false),
                SignupMessage(2, MessageOwner.CURL, "新規登録の準備をしてます。ちょっと待っててね。", false),
                SignupMessage(3, MessageOwner.CURL, "始めにあなたの髪質を教えてください。", true),
                SignupMessage(4, MessageOwner.CURL, "%sですね！", false),
                SignupMessage(5, MessageOwner.CURL, "あなたの性別を教えてください。あっ、教えたくない場合は「答えない」を選んでね。", true),
                SignupMessage(6, MessageOwner.CURL, "教えてくれてありがとう！", false),
                SignupMessage(7, MessageOwner.CURL, "次はアプリの中で使うアイコンを選んでね。", true),
                SignupMessage(8, MessageOwner.CURL, "もう少しだけあなたのことを教えてね。", false),
                SignupMessage(9, MessageOwner.CURL, "あ、あなたをなんて呼べばいい？", true),
                SignupMessage(10, MessageOwner.CURL, "%sさんですね！", false),
                SignupMessage(11, MessageOwner.CURL, "登録するメールアドレスを教えてください。このメールアドレスはログインする時に使うよ。" , true),
                SignupMessage(12, MessageOwner.CURL, "最後にパスワードを教えてください。", true),
                SignupMessage(13, MessageOwner.CURL, "新規登録しています。ちょっと待っててね。", false),
                SignupMessage(14, MessageOwner.CURL, "お疲れ様でした。新規登録が完了しました。", false),
                SignupMessage(15, MessageOwner.CURL, "CURLをお使いいただけます。これからよろしくお願いします！", true)
        )
    }
}
