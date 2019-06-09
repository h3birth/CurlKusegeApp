package birth.h3.app.curl_kusegeapp.model.entity

import birth.h3.app.curl_kusegeapp.model.enums.MessageOwner

data class SignupMessage(
        val id: Int,
        val owner: MessageOwner,
        val message: String,
        val wait: Boolean,
        val response: Boolean,
        val userMessage: SignUpAnswerMessages?
) {
    companion object {
        fun signUpMessages() = listOf(
                SignupMessage(1, MessageOwner.CURL, "Curlをインストールいただきありがとうございます！", false, false, null),
                SignupMessage(2, MessageOwner.CURL, "新規登録の準備をしてます。ちょっと待っててね。", false, false, null),
                SignupMessage(3, MessageOwner.CURL, "始めにあなたの髪質を教えてください。", true, false, SignUpAnswerMessages("さらさら","ちょいくせ", "ちょうくせ")),
                SignupMessage(4, MessageOwner.CURL, "%sですね！", false, true, null),
                SignupMessage(5, MessageOwner.CURL, "あなたの性別を教えてください。あっ、教えたくない場合は「答えない」を選んでね。", true, false, SignUpAnswerMessages("男性","女性", "答えない")),
                SignupMessage(6, MessageOwner.CURL, "教えてくれてありがとう！", false, false, null),
                SignupMessage(7, MessageOwner.CURL, "もう少しだけあなたのことを教えてね。", true, false, SignUpAnswerMessages("","", "次へ")),
                SignupMessage(8, MessageOwner.CURL, "あ、あなたをなんて呼べばいいかな？名前を教えて！", false, false, null),
                SignupMessage(9, MessageOwner.CURL, "%sさんですね！", false, true, null),
                SignupMessage(10, MessageOwner.CURL, "登録するメールアドレスを教えてください。このメールアドレスはログインする時に使うよ。" , true, false, null),
                SignupMessage(11, MessageOwner.CURL, "最後にパスワードを教えてください。", true, false, null),
                SignupMessage(12, MessageOwner.CURL, "新規登録しています。ちょっと待っててね。", false, false, null),
                SignupMessage(13, MessageOwner.CURL, "お疲れ様でした。新規登録が完了しました。", false, false, null),
                SignupMessage(14, MessageOwner.CURL, "CURLをお使いいただけます。これからよろしくお願いします！", true, false, null)
        )
    }
}
