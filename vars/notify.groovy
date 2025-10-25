def call(String buildStatus = 'UNKNOWN') {
    def subject = ""
    def body = ""

    switch (buildStatus) {
        case 'SUCCESS':
            subject = "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            body = """Good news! The pipeline succeeded.

            • Job: ${env.JOB_NAME}
            • Build Number: ${env.BUILD_NUMBER}
            • URL: ${env.BUILD_URL}
            """
            break
        case 'FAILURE':
            subject = "❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            body = """The pipeline failed.

            • Job: ${env.JOB_NAME}
            • URL: ${env.BUILD_URL}
            • Check console logs for error details.
            """
            break
        case 'ABORTED':
            subject = "🚫 ABORTED: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            body = """The build was manually aborted.

            • Job: ${env.JOB_NAME}
            • URL: ${env.BUILD_URL}
            """
            break
        default:
            subject = "⚠️ UNKNOWN STATUS: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            body = """Pipeline ended with unknown status.

            • Job: ${env.JOB_NAME}
            • URL: ${env.BUILD_URL}
            """
            break
    }
    echo "📧 Sending email notification for ${buildStatus}..."
    mail to: 'ayoobkibrahim109@gmail.com',
        subject: subject,
        body: body
}

