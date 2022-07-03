import SwiftUI
import shared

@main
struct iOSApp: App {
	let sdk = Database(databaseDriverFactory: DatabaseDriverFactory())
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
